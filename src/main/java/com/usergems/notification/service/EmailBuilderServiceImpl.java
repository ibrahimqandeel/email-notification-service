package com.usergems.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usergems.notification.dto.EmailDto;
import com.usergems.notification.dto.EventDto;
import com.usergems.notification.dto.GuestInfoDto;
import com.usergems.notification.dto.SalesDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmailBuilderServiceImpl implements EmailBuilderService {

    private final SalesService salesService;
    private final CalendarService calendarService;
    private final GuestInfoService guestInfoService;
    private final EmailService emailService;

    public EmailBuilderServiceImpl(SalesService salesService, CalendarService calendarService, GuestInfoService guestInfoService, EmailService emailService) {
        this.salesService = salesService;
        this.calendarService = calendarService;
        this.guestInfoService = guestInfoService;
        this.emailService = emailService;
    }


    @Override
    public void buildTodayEmails() {

        Map<String, List<EventDto>> salesEventsMap = getTodayEventsForAllSales();

        List<EventDto> allEventsToday = salesEventsMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Map<Integer, Set<GuestInfoDto>> guestsEventsMap = getGuestsInfoForAllEvents(allEventsToday);

        if (guestsEventsMap.size() > 0) {
            List<EmailDto> emails = new ArrayList<>();

            salesEventsMap.forEach((k, v) -> {
                Set<GuestInfoDto> guests = null;
                for (EventDto event : v) {
                    guests = guestsEventsMap.get(event.getId());
                }
                emails.add(createEmailFromGuestsInfo(k, guests));
            });

            emails.forEach(email -> {
                emailService.saveEmail(email);
                System.out.println(email);
            });
        }
    }

    private Map<String, List<EventDto>> getTodayEventsForAllSales() {
        List<SalesDto> allUserGemsSales = salesService.findAllWhenApiKeyNotEmpty();

        Map<String, List<EventDto>> salesEventsMap = new HashMap<>();
        for (SalesDto sales : allUserGemsSales) {
            salesEventsMap.put(sales.getEmail(), getTodayEventsBySalesApiKey(sales.getApiKey()));
        }

        return salesEventsMap;
    }

    private List<EventDto> getTodayEventsBySalesApiKey(String salesApiKey) {
        return calendarService.getTodayEventsBySalesApiKey(salesApiKey);
    }

    private Map<Integer, Set<GuestInfoDto>> getGuestsInfoForAllEvents(List<EventDto> allEventsToday) {
        Map<Integer, Set<GuestInfoDto>> eventGuestsMap = new HashMap<>();
        for (EventDto event : allEventsToday) {
            Set<String> guestEmails = extractGuestEmailsFromEvent(event);
            Set<GuestInfoDto> guestsInfo = getAllGuestsInfo(guestEmails);
            if (guestsInfo.size() > 0) {
                eventGuestsMap.put(event.getId(), guestsInfo);
            }
        }
        return eventGuestsMap;
    }

    private Set<String> extractGuestEmailsFromEvent(EventDto event) {
        Set<String> guestEmails = new HashSet<>();
        for (String guestEmail : event.getAccepted()) {
            if (!guestEmail.contains("@usergems.com")) {
                guestEmails.add(guestEmail);
            }
        }
        return guestEmails;
    }

    private Set<GuestInfoDto> getAllGuestsInfo(Set<String> guestEmails) {
        Set<GuestInfoDto> allGuestsInfo = new HashSet<>();
        guestEmails.forEach(email -> {
            Optional<GuestInfoDto> guest = getGuestInfo(email);
            guest.ifPresent(allGuestsInfo::add);
        });
        return allGuestsInfo;
    }

    private Optional<GuestInfoDto> getGuestInfo(String personEmail) {
        return guestInfoService.getGuestInfoByEmail(personEmail);
    }

    private EmailDto createEmailFromGuestsInfo(String emailReceiver, Set<GuestInfoDto> guestsInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        EmailDto email = new EmailDto();
        email.setEmailReceiver(emailReceiver);
        try {
            email.setBody(objectMapper.writeValueAsString(guestsInfo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return email;
    }
}
