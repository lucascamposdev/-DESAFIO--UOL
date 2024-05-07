package presto.com.Desafio.UOL.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import presto.com.Desafio.UOL.domain.user.UserGroups;
import presto.com.Desafio.UOL.infra.exceptions.UnavailableCodenamesException;
import presto.com.Desafio.UOL.service.CodenameService;

@Component
public class CodenameHandler {

    @Autowired
    private CodenameService codenameService;

    public String getCodename(UserGroups group){

        var groupList = group == UserGroups.LIGADAJUSTICA ?
        codenameService.getLigadajustica() : codenameService.getVingadores();

        if(!codenameService.hasAvailableCodenames(groupList)){
            throw new UnavailableCodenamesException("Não há mais codinomes disponíveis.");
        }

        String codename = groupList.get(0);
        groupList.remove(0);

        return codename;
    }
}
