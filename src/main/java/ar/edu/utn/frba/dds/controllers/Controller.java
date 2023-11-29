package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.exceptions.AccessDeniedException;
import ar.edu.utn.frba.dds.exceptions.SessionNotLogInException;
import ar.edu.utn.frba.dds.models.community.Community;
import ar.edu.utn.frba.dds.models.community_member.CommunityMember;
import ar.edu.utn.frba.dds.models.community_member.RolInCommunity;
import ar.edu.utn.frba.dds.models.users.RolType;
import ar.edu.utn.frba.dds.models.users.User;
import ar.edu.utn.frba.dds.repositories.users.UserRepository;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller implements WithSimplePersistenceUnit {

    protected User usuarioLogueado(Context ctx) {
        if(ctx.sessionAttribute("user_id") == null){

            throw new SessionNotLogInException();
        }else{
            return UserRepository.getInstance()
                    .find(User.class, ctx.sessionAttribute("user_id"));
        }
    }

    //solo para admin de community

    //TODO TESTEAR

    protected void checkRolTypeForSpecificCommunity(CommunityMember member, RolInCommunity rol) {
        AtomicBoolean hasRolType = new AtomicBoolean(false);

            if (member.getRolInCommunity().equals(rol)) {
                hasRolType.set(true);
            }

        if (!hasRolType.get()){
            throw new AccessDeniedException();
        }
    }

    protected void checkRolTypeForSpecificCommunity(RolInCommunity rolToValidate, Community community, User user){

        community.getMembers().forEach(communityMember ->
        {
            if(communityMember.getPerson().getUser().getId().equals(user.getId())){
                this.checkRolTypeForSpecificCommunity(communityMember, rolToValidate);
            }
        });
    }

    protected void checkRolType(User user, RolType rol){

        AtomicBoolean hasRolType = new AtomicBoolean(false);

        if ( user.getRolType().equals(rol)) {
            hasRolType.set(true);
        }

        if (!hasRolType.get()){
            throw new AccessDeniedException();
        }
    }

    protected void checkRolType2(User user, RolType rol1, RolType rol2) {
        AtomicBoolean hasRolType = new AtomicBoolean(false);

        if ( user.getRolType().equals(rol1) || user.getRolType().equals(rol2)) {
            hasRolType.set(true);
        }

        if (!hasRolType.get()){
            throw new AccessDeniedException();
        }
    }

}
