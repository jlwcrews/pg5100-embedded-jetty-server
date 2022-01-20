const $ = (element) => {
    return document.querySelector(element)
}

$("#pingButton").addEventListener("click", async function() {
  $("#clickedText").innerHTML = await getPing();
});

$("#apiButton").addEventListener("click", function() {
  window.location.href="http://localhost:8080/api";
});

$("#cookie").addEventListener("click", function() {
  window.location.href="http://localhost:8080/cookie";
});

const getPing = async () => {
    const response = await fetch("/ping")
    return response.status
};
