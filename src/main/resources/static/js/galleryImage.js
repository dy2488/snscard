$(document).ready(function() {
    $.post("/info/getAllImage", function(data) {
        const imageGallery = $("#image-gallery");
        data.forEach(function(imageUrl) {
            const img = $("<img>").attr("src", imageUrl);
            imageGallery.append(img);
        });
    });
});
