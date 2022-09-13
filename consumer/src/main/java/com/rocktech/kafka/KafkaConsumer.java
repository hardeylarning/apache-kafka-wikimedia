package com.rocktech.kafka;

import com.rocktech.model.WikimediaData;
import com.rocktech.repository.WikimediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private WikimediaDataRepository repository;

    @KafkaListener(topics = "wikimedia_recent_change", groupId = "myGroup")
    public void consume(String eventMessage) {
        WikimediaData wikimediaData = new WikimediaData();
        log.info(String.format("Event message received -> %s", eventMessage));
        wikimediaData.setWikiEventData(eventMessage);
        repository.save(wikimediaData);
    }
}
