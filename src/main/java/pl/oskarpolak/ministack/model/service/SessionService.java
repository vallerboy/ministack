package pl.oskarpolak.ministack.model.service;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.sql.DataSourceDefinitions;
        import javax.persistence.NamedAttributeNode;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class SessionService {
    private int userId;
    private String nickname;
    private boolean isLogin;
}
