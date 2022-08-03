package com.company.entity;

import com.company.entity.enums.Permissions;
import com.company.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Role role;

    private String emailCode;
    private boolean enabled;

    private boolean AccountNonLocked=true;

    private boolean AccountNonExpired=true;

    private boolean CredentialsNonExpired=true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permissions> permissionsList=this.role.getPermissionsList();
        Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
        for (Permissions permissions : permissionsList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permissions.name()));
        }
//        for (Huquq huquq : huquqList) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return huquq.name();
//                }
//            });
//        }
        return grantedAuthorities;
    }


    public User(String fullName, String email,String username, String password, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.username=username;
        this.password = password;
        this.role = role;

    }
    public User(String fullName, String email,String username, String password, Role role,boolean enabled) {
        this.fullName = fullName;
        this.email = email;
        this.username=username;
        this.password = password;
        this.role = role;
        this.enabled=enabled;

    }
}
