var StringBuffer = function(str) {
	if (str != undefined)
		this.value = new Array(str);
	else
		this.value = new Array();

	if (StringBuffer.prototype.append == undefined) {
		StringBuffer.prototype.append = function(str) {
			this.value.push(str);
			return this;
		}
	}

	StringBuffer.prototype.toString = function() {
		return this.value.join("");
	}

	if (StringBuffer.prototype.clear == undefined) {
		StringBuffer.prototype.clear = function() {
			this.value = [];
		}
	}
}

var urlParams;
(window.onpopstate = function () {
    var match,
        pl     = /\+/g,  // Regex for replacing addition symbol with a space
        search = /([^&=]+)=?([^&]*)/g,
        decode = function (s) { return decodeURIComponent(s.replace(pl, " ")); },
        query  = window.location.search.substring(1);

    urlParams = {};
    while (match = search.exec(query))
       urlParams[decode(match[1])] = decode(match[2]);
})();