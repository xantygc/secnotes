package com.axpo.secnotes.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter @AllArgsConstructor
@Builder @EqualsAndHashCode
public class Note
{
    private UUID id = UUID.randomUUID();
    private String text;
    private DeleteTimeType deleteTimeType;
    private LocalDateTime localDateTime;

    public Note (String text)
    {
        this.text=text;
        this.localDateTime=LocalDateTime.now();
        this.deleteTimeType=DeleteTimeType.AFTER_READING;
    }

    public Note (UUID uuid)
    {
        this.id = uuid;
    }
}
