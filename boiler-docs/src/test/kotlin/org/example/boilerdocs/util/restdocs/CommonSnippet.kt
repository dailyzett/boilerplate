package org.example.boilerdocs.util.restdocs

class CommonSnippet {
    companion object {
        fun company(): List<RestDocField> {
            return listOf(
                "company" type OBJECT means "회사 오브젝트",
                "company.name" type STRING means "회사 이름",
                "company.catchPhrase" type STRING means "회사 슬로건",
                "company.bs" type STRING means "회사 비즈니스",
            )
        }

        fun address(): List<RestDocField> {
            return listOf(
                "address" type OBJECT means "주소 오브젝트",
                "address.street" type STRING means "주소 - 거리",
                "address.suite" type STRING means "주소 - 번지",
                "address.city" type STRING means "주소 - 도시",
                "address.zipcode" type STRING means "주소 - 우편 번호",
                "address.geo" type OBJECT means "주소 - 좌표 오브젝트",
                "address.geo.lat" type STRING means "주소 - 위도",
                "address.geo.lng" type STRING means "주소 - 경도",
            )
        }

        fun addressArray(): List<RestDocField> {
            return address().map { it.prependPath("[].")}
        }

        fun companyArray(): List<RestDocField> {
            return company().map { it.prependPath("[].") }
        }
    }
}