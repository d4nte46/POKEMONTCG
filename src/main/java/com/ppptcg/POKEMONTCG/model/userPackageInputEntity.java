package com.ppptcg.POKEMONTCG.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class userPackageInputEntity {

    private String packageName;
    private String packageNo;
    private Short setNo;
    private String variety;
}
