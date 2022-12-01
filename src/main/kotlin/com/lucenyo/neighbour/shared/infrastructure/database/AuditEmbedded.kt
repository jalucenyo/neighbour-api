//package com.lucenyo.neighbour.shared.infraestructure.database
//
//import com.lucenyo.neighbour.shared.domain.Audit
//import org.springframework.data.annotation.CreatedDate
//import org.springframework.data.annotation.LastModifiedDate
//import java.time.OffsetDateTime
//import javax.persistence.Column
//import javax.persistence.Embeddable
//
//@Embeddable
//data class AuditEmbedded (
//
//  @Column(name = "created_date", nullable = false, updatable = false)
//  @CreatedDate
//  var createDate: OffsetDateTime? = null,
//
//  @Column(name = "modified_date")
//  @LastModifiedDate
//  var modifiedDate: OffsetDateTime? = null,
//
//)
//
//fun AuditEmbedded.toDomain() = Audit(
//  createDate = createDate,
//  modifiedDate = modifiedDate
//)