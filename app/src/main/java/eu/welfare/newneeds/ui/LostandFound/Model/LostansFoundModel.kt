package eu.welfare.newneeds.ui.LostandFound.Model

interface LostansFoundModel {

    var pid: Int;
    var LostOrFound: String;
    var Location: String;
    var DateofMissingOrFound: String;
    var Breed: String;
    var Gender: String;
    var Color: String;
    var Size: String;
    var IdentificationMarks: String;
    var ApproximateAge: Int;
    var WearingCollar: Boolean;
    var Email: String;
    var Phone: Int;
    var OtherInfo: String;
}