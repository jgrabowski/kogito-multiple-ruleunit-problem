package pl.perfectsource.kogito.ruleunit;

import org.drools.ruleunits.api.RuleUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import pl.perfectsource.kogito.rules.First;
import pl.perfectsource.kogito.rules.FirstQueryFirst;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/")
public class DirectRuleUnitService {

    @Inject
    RuleUnit<First> firstRuleUnit;

    public Response invoke() {
        First f = new First();
        RuleInput input = new RuleInput();
        input.setTest("Hello");
        f.getInput().append(input);
        RuleUnitInstance<First> instance = firstRuleUnit.createInstance(f);
        List<RuleOutput1> output = FirstQueryFirst.execute(instance);
        if(output.isEmpty()) {
            return Response.ok("Not fired").build();
        }else {
            return Response.ok("Returned: " + output.get(0).getTest1()).build();
        }
    }
}
