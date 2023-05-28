var requests, OpenLibrary;
(function () {
    'use strict'; //для кросс-доменных запросов в библиотеке JQuery

    $.support.cors = true

    requests = {
        get: function(url, callback) {
            $.get(url, function(results) {
            }).done(function(data) {
                if (callback) { callback(data); }
            });
        },
        // post: function(url, data, callback) {
        //     $.ajax.post(url, data, function(results) {
        //     }).done(function(data) {
        //         if (callback) { callback(data); }
        //     });
        // }
        post: function(url, bookAPI, callback) {
            $.ajax({
                url: url,
                type: 'POST',
                data: bookAPI,
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function(data) {
                    alert(data);
                },
                error: function (error){
                    alert(error);
                }
            });
         }
    };


    OpenLibrary = {
        search: function(query, callback) {
            var url = "https://openlibrary.org/search/inside.json?q=" + query
            requests.get(url, function(response) {
                var match = response.hits.hits[0];
                var book = {
                    id: 1,
                    title: "ford",
                    author: "Mary",
                    isbn: "12345"
                };
                OpenLibrary.saveBook(book);
                callback(response.hits.hits);
            });
        },
        saveBook: function(data) {
            var url = "/save";
            var bookAPI = JSON.stringify(data);
            requests.post(url, bookAPI, function(response) {
                console.log(response);
            });
        }
    };


    var debounce = function (func, threshold, execAsap) {
        var timeout;
        return function debounced () {
            var obj = this, args = arguments;
            function delayed () {
                if (!execAsap)
                    func.apply(obj, args);
                timeout = null;
            };

            if (timeout) {
                clearTimeout(timeout);
            } else if (execAsap) {
                func.apply(obj, args);
            }
            timeout = setTimeout(delayed, threshold || 100);
        };
    };

    $(document).keyup('#booksearch', debounce(function(event) {
        $('.bookmatch').empty();
        $('.bookmatch').addClass('loader');
    }, 100, false));

    $(document).keyup('#booksearch', debounce(function(event) {
        OpenLibrary.search($('#booksearch input').val(), function(results) {
            var match = results[0];
            $('.bookmatch').removeClass('loader');
            $('.bookmatch').append(
                '<a href="https://openlibrary.org' + match.edition.key + '">' +
                '<img src="' + match.edition.cover_url + '">' +
                '</a>'
            );
        });
    }, 1000, false));

}());
