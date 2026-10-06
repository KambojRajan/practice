package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Permissions {
    Long id;
    boolean canRead;
    boolean canWrite;
    boolean canExecute;
    boolean canDelete;

    public Permissions() {
        canRead = true;
        canWrite = canExecute = canDelete = false;
    }
}
