package pl.limescode.gkcart.validator;

import org.springframework.stereotype.Component;
import pl.limescode.gkcart.dto.ProductCreateDto;
import pl.limescode.gkcart.dto.ProductDto;
import pl.limescode.gkcart.exception.ValidationException;

@Component
public class ProductValidator {

    public void validate(ProductDto dto) {
        if (dto.getId() == null) {
            throw new ValidationException("Product id can not be empty");
        }
        if (dto.getPrice() < 0) {
            throw new ValidationException("Price can not be lower than 0");
        }
        if (dto.getName().isBlank()) {
            throw new ValidationException("Name of the product can't be blank");
        }
    }

    public void validate(ProductCreateDto dto) {
        if (dto.getPrice() < 0) {
            throw new ValidationException("Price can not be lower than 0");
        }
        if (dto.getName().isBlank()) {
            throw new ValidationException("Name of the product can't be blank");
        }
    }
}
