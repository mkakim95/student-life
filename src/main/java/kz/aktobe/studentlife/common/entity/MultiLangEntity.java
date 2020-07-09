package kz.aktobe.studentlife.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class MultiLangEntity extends BaseEntity {

    @Column(name = "name_ru")
    protected String nameRu;

    @Column(name = "name_kz")
    protected String nameKz;

    @Column(name = "name_en")
    protected String nameEn;
}
