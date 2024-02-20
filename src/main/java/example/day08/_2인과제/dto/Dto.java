package example.day08._2인과제.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Dto {
    private int no;
    private String content;
    private String date;
    private boolean state;

    public Dto(int no, String content, String date) {
        this.no = no;
        this.content = content;
        this.date = date;
    }
}
