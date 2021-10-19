package bankpojos;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Transactions {
    private Double amount;
    private LocalDate date;
    private Integer transactionId;
    private String transactionName;





}
