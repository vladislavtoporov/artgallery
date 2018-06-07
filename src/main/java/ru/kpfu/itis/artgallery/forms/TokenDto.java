package ru.kpfu.itis.artgallery.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.artgallery.models.Token;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String value;
    private String userLogin;

    public static TokenDto from(Token model) {
        return TokenDto.builder()
                .value(model.getValue())
                .userLogin(model.getUser().getLogin())
                .build();
    }

    public static List<TokenDto> from(List<Token> models) {
        return models.stream().map(TokenDto::from).collect(Collectors.toList());
    }
}
