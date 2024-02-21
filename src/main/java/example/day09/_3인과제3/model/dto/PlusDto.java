package example.day09._3인과제3.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class PlusDto {
    private int no;
    private String content;
    private String point;
    private String date;
}