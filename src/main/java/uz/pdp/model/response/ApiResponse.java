package uz.pdp.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class ApiResponse<T> {
    private T data;
    private String errorMessage = "";
    private boolean success;

    public ApiResponse(T data) {
        this.data = data;
        this.success = true;
        this.errorMessage = "";
    }

    public ApiResponse(T data, boolean success) {
        this.data = data;
        this.errorMessage = "";
        this.success = success;
    }

    public ApiResponse(String errorMessage) {
        this.data = null;
        this.success = false;
        this.errorMessage = errorMessage;
    }
}
