var DataService = function () {
    var self = this;

    self.getItems = function (callback, myErrorFunc) {
        $.ajax({
            url: 'http://localhost:8080/items',
            method: 'GET',
            success: callback,
            error: myErrorFunc
        });
    }

    self.vendItem = function(itemId, balance, callback, myErrorFunc) {
       $.ajax({
           url: 'http://localhost:8080/money/' + balance + '/item/' + itemId,
           method: 'GET',
           datatype: 'JSON',
           success: callback,       
           error: myErrorFunc
       });
    }
}