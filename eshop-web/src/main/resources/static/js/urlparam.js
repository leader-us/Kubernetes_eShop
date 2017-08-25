function getQueryString(url, paramName) {
    var result = "";
    if (!url.length) return result;
    var parts = url.split(/\?|\&/);
    for (var i = 0, len = parts.length; i < len; i++) {
        var tokens = parts[i].split("=");
        if (tokens[0] == paramName) {
            return decodeURIComponent(tokens[1]);
        }
    }
    return result;
}
