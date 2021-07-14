package enums;


import lombok.Getter;

@Getter
public enum SessionVariables {

    RESPONSE_CODE ("responseCode"),
    ORDER_RESPONSE ("orderResponse"),
    ORDER_ID ("orderId");

    private String key;

    SessionVariables(final String key) {this.key = key;}
}
