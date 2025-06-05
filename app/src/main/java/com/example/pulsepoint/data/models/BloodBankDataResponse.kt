package com.example.pulsepoint.data.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BloodBankDataResponse(
    @SerializedName("active")
    var active: String?,
    @SerializedName("catalog_uuid")
    var catalogUuid: String?,
    @SerializedName("count")
    var count: Int?,
    @SerializedName("created")
    var created: String?,
    @SerializedName("created_date")
    var createdDate: String?,
    @SerializedName("desc")
    var desc: String?,
    @SerializedName("external_ws")
    var externalWs: Int?,
    @SerializedName("external_ws_url")
    var externalWsUrl: String?,
    @SerializedName("field")
    var `field`: List<Field?>?,
    @SerializedName("index_name")
    var indexName: String?,
    @SerializedName("limit")
    var limit: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("offset")
    var offset: String?,
    @SerializedName("org")
    var org: List<String?>?,
    @SerializedName("org_type")
    var orgType: String?,
    @SerializedName("records")
    var records: List<Record?>?,
    @SerializedName("sector")
    var sector: List<String?>?,
    @SerializedName("source")
    var source: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("target_bucket")
    var targetBucket: TargetBucket?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("total")
    var total: Int?,
    @SerializedName("updated")
    var updated: Int?,
    @SerializedName("updated_date")
    var updatedDate: String?,
    @SerializedName("version")
    var version: String?,
    @SerializedName("visualizable")
    var visualizable: String?
) {
    @Keep
    data class Field(
        @SerializedName("id")
        var id: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("type")
        var type: String?
    )

    @Keep
    data class Record(
        @SerializedName("_address")
        var address: String?,
        @SerializedName("_apheresis")
        var apheresis: String?,
        @SerializedName("_blood_bank_name")
        var bloodBankName: String?,
        @SerializedName("_blood_component_available")
        var bloodComponentAvailable: String?,
        @SerializedName("_category")
        var category: String?,
        @SerializedName("_city")
        var city: String?,
        @SerializedName("_contact_no")
        var contactNo: String?,
        @SerializedName("_contact_nodal_officer")
        var contactNodalOfficer: String?,
        @SerializedName("_date_license_obtained")
        var dateLicenseObtained: String?,
        @SerializedName("_date_of_renewal")
        var dateOfRenewal: String?,
        @SerializedName("_district")
        var district: String?,
        @SerializedName("_email")
        var email: String?,
        @SerializedName("_email_nodal_officer")
        var emailNodalOfficer: String?,
        @SerializedName("_fax")
        var fax: String?,
        @SerializedName("_helpline")
        var helpline: String?,
        @SerializedName("_latitude")
        var latitude: Double?,
        @SerializedName("_license__")
        var license: String?,
        @SerializedName("_longitude")
        var longitude: Double?,
        @SerializedName("_mobile")
        var mobile: String?,
        @SerializedName("_mobile_nodal_officer")
        var mobileNodalOfficer: String?,
        @SerializedName("_nodal_officer_")
        var nodalOfficer: String?,
        @SerializedName("pincode")
        var pincode: String?,
        @SerializedName("_qualification_nodal_officer")
        var qualificationNodalOfficer: String?,
        @SerializedName("_service_time")
        var serviceTime: String?,
        @SerializedName("sr_no")
        var srNo: Int?,
        @SerializedName("_state")
        var state: String?,
        @SerializedName("_website")
        var website: String?
    )

    @Keep
    data class TargetBucket(
        @SerializedName("field")
        var `field`: String?,
        @SerializedName("index")
        var index: String?,
        @SerializedName("type")
        var type: String?
    )
}