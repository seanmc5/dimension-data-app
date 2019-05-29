package com.gmail.seanmc560.dimensiondataapp.persistence;

import com.gmail.seanmc560.dimensiondataapp.persistence.shared.timestampable.Timestampable;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Entity
@Table(name = "SERVER")
@Setter
public class ServerEntity extends Timestampable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  @Column(name = "ID")
  private Long id;
  @Column(name = "NAME")
  private String name;
  @Column(name = "DESCRIPTION")
  private String description;
}
