package uz.salikhdev.springprojct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageDto {
    private String message;
    private boolean status;
    private String timestamp;
}
