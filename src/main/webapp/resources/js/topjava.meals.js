
const mealAjaxUrl = "ajax/profile/meals/";


function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get("ajax/profile/meals/", updateTableByData);
}



    // $("#datetimepicker").datetimepicker();



$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (data, type, row) {
                        if (type === "display") {

                           return new Date(Date.parse(data)).toLocaleDateString("ru") +
                               " " +
                               new Date(Date.parse(data)).toLocaleTimeString("ru").substring(0, 5);;
                        }
                        return data;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (data.excess === true) {
                    $(row).attr("data-mealExcess", true);
                }
                else{
                    $(row).attr("data-mealExcess", false);
                }
            }
        }),
        updateTable: function () {
            $.get(mealAjaxUrl, updateTableByData);
        }
    });
});