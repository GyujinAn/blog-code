package spring.decorator01.repo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */
@Component
public class TeamRepository {

    Set<String> teams = new HashSet();

    @PostConstruct
    private void init(){
        teams.add("DEV");
        teams.add("OPS");
    }

    public boolean contains(String team) {
        return teams.contains(team);

    }
}
