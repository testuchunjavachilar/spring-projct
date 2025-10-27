package uz.salikhdev.springprojct.dto;

import lombok.Builder;

@Builder
public record TodoListDto(Long id, String title) {
}
