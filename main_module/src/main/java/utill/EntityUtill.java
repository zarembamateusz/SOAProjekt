package utill;

import entity.UserEntity;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@UtilityClass
public class EntityUtill {
    public <T> String extractId(Supplier<String> f) {

        val id = f.get();
        return id == null ? UUID.randomUUID().toString() : id;
    }
}
