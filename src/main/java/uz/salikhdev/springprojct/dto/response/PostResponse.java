package uz.salikhdev.springprojct.dto.response;

public record PostResponse(
        Long id,
        String title,
        String description,
        String resourceUrl,
        String author
) {
}
