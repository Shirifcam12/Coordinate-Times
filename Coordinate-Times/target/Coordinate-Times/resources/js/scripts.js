/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initDialog() {
 $("#idOfDialogPlaceHolder").dialog({
     modal: true,
     buttons: {
            SomeButton: function () {
                $("#justAButton").click();
            },
            Close: function () {
                $(this).dialog("close");
            }
     },
 });
}
