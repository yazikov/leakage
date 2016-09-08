/**
 * Created by nikolay on 06.09.15.
 */
var mapContainer;
var col1;
var col2;
var col3;

$(document).ready(function() {

    mapContainer = $('.map_container');
    //col1 = $('.col1');
    //col2 = $('.col2');
    //col3 = $('.col3');

    if (mapContainer != null) {
        //setMapContainerSize(mapContainer);
        //scrollToCenter(mapContainer);
    }

    //setColumnSize();
    $(window).resize(function() {
        if (mapContainer != null) {
            //setMapContainerSize();
        }
        //setCorrectHeight();
    });

    //setCorrectHeight();

    var pressed = false;
    var start = undefined;
    var startX, startWidth;

    $('select').select2();

    $(".datepicker").datepicker({
        firstDay: 1,
        dateFormat: "dd.mm.yy",
        dayNames: [ "Воскресение", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" ],
        dayNamesMin: [ "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" ],
        dayNamesShort: [ "Воск", "Пон", "Втор", "Сред", "Четв", "Пят", "Суб" ],
        monthNames: [ "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" ],
        monthNamesShort: [ "Янв", "Фев", "Март", "Апр", "Май", "Июнь", "Июль", "Авг", "Сен", "Окт", "Нояб", "Дек" ]
    });

    $(".validator-form").validator().on('submit', function (e) {
        if ($("#startDate") && $("#endDate")) {
            var start = $("#startDate").val();
            var end = $("#endDate").val();
            var parts = start.split('\.');
            var startDate = new Date(parts[2] + '-' + parts[1] + '-' + parts[0]);
            parts = end.split('\.');
            var endDate = new Date(parts[2] + '-' + parts[1] + '-' + parts[0]);
            if (endDate.getTime() - startDate.getTime() < 0) {
                alert("Начальная дата должна быть меньше или равна чем конечная!");
                e.preventDefault();
            }
            var action = $(".validator-form").attr("action");
            if (action.indexOf('graphic') != -1 && endDate.getTime() - startDate.getTime() > 30 * 24 * 60 * 60 * 1000) {
                alert("Период для построения графика должен быть менее 30 суток!");
                e.preventDefault();
            }
        }
    });

    $('body').on('click', '.btnHide', function() {
       var me = $(this);
        var span = me.find('span');
        var col = me.parents('.col').first();
        var centerCol = $('.col-center');
        var centerSize = centerCol.data('size');
        if(span.hasClass('glyphicon-minus')) {
            span.removeClass('glyphicon-minus');
            span.addClass('glyphicon-plus');
            centerCol.removeClass('col-xs-' + centerSize);
            centerCol.addClass('col-xs-' + (centerSize + 1));
            centerCol.data('size', centerSize + 1);

            col.removeClass('col-xs-2');
            col.addClass('col-xs-1');

        } else {
            span.removeClass('glyphicon-plus');
            span.addClass('glyphicon-minus');
            centerCol.removeClass('col-xs-' + centerSize);
            centerCol.addClass('col-xs-' + (centerSize - 1));
            centerCol.data('size', centerSize - 1);

            col.removeClass('col-xs-1');
            col.addClass('col-xs-2');
        }
    });

});

function setCorrectHeight() {
    var height = $('body').height();
    var headerHeight = $('header').height();
    $('.map_container').css('min-height', height - headerHeight - 50 + "px");
}

function getSensorById(id) {
    for (var i = 0; i < sensors.length; i++) {
        var sensor = sensors[i];
        if (sensor.id == id) {
            return sensor;
        }
    }
}

function getCircleById(id) {
    for (var i = 0; i < sensors.length; i++) {
        var sensor = sensors[i];
        if (sensor.id == id) {
            return circles[i];
        }
    }
}

function scrollToCenter(elem) {
    var childrens = elem.children();
    if (childrens != null && childrens.length > 0) {
        var children = $(childrens[0]);
        var width = elem.width();
        var inWidth = children.width();
        if (width < inWidth) {
            elem.scrollLeft((inWidth - width) / 2);
        }
        var height = elem.height();
        var inHeight = children.height();
        if (height < inHeight) {
            elem.scrollTop((inHeight - height) / 2);
        }

    }
}

function setMapContainerSize() {
    var map = ('#map');
    map.height((496 * map.width() / 1024) + 'px');
}

function setColumnSize() {
    var height = col2.height() + 20;
    if (height > 600) {
        col1.height(height);
        col3.height(height);
        col1.css("min-height", height + "px");
        col3.css("min-height", height + "px");
    } else {
        col1.height(600);
        col3.height(600);
        col1.css("min-height", "600px");
        col3.css("min-height", "600px");
    }
}
