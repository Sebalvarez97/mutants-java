package com.mutants.api.domain.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(value = "dna")
public class DnaMongo {

  @Id private String id;

  @Field("dna_hash")
  private String dnaHash;

  @Field("is_mutant")
  private boolean mutant;

  @Field("mutant_sequences")
  private int sequences;
}
