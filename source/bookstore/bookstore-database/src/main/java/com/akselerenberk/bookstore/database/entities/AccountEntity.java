package com.akselerenberk.bookstore.database.entities;

import com.akselerenberk.bookstore.core.models.Role;
import com.akselerenberk.bookstore.core.models.VerificationLevel;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@ToString
@Entity(name = "app_account")
@Table(name = "ACCOUNT")
public class AccountEntity implements UserDetails {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(
            name = "account_seq",
            sequenceName = "ACCOUNT_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotNull
    @Column(name = "VERIFICATION_LEVEL", nullable = false)
    @Enumerated(EnumType.STRING)
    private VerificationLevel verificationLevel;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "USERNAME", length = 50, unique = true, nullable = false)
    private String username;


    @NotBlank
    @NotNull
    @Size(max = 255)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "ACCOUNT_NON_EXPIRED")
    private boolean accountNonExpired;

    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired;

    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked;

    /*
     * Get all roles associated with the Account
     */
    @ElementCollection(
            targetClass = Role.class,
            fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_USER", joinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "LAST_UPDATED_TIME", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @Column(name = "CREATION_TIME", nullable = false)
    @CreationTimestamp
    private LocalDateTime creationTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountEntity that = (AccountEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId()) ||
                getUsername() != null && Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
