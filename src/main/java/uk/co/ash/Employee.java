package uk.co.ash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FixedLengthRecord(length = 10, paddingChar = ' ')
public class Employee {

    @DataField(pos = 1, length = 8)
    String name;

    @DataField(pos = 2, length = 2)
    Integer id;
}
