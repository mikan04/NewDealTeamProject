document.addEventListener("DOMContentLoaded", function () {
  let links = document.querySelectorAll(".nav-link");
  let path = window.location.pathname.split("/")[2];

  for (let link of links) {
    if (link.dataset.path === path) {
      link.classList.add("active");
    } else {
      link.classList.remove("active");
    }
  }
});
