package kz.aktobe.studentlife.entity;

import kz.aktobe.studentlife.common.entity.MultiLangEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Специальность
 */
@Entity
@Table(name = "professions")
public class Profession extends MultiLangEntity {
}
