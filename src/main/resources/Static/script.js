const images = document.querySelectorAll(".Header-slider ul img");
const prevButton = document.querySelector(".control-prev");
const nextButton = document.querySelector(".control-next");

let n = 0;


function slider() {
    for (let index = 0; index < images.length; index++) {
        images[index].style.display = 'none';
    }
    images[n].style.display = 'block';
}
slider();

prevButton.addEventListener("click", (e) => {
    if (n > 0) { n--; }
    else { n = images.length - 1; }
    slider();
})

nextButton.addEventListener("click", (e) => {
    if (n < images.length - 1) { n++; }
    else { n = 0; }
    slider();
})


//Scroll Product 3 for mouse
const rows = document.querySelectorAll(".products");
rows.forEach(row => {
    row.addEventListener("wheel", (e) => {
        e.preventDefault();
        row.scrollLeft += e.deltaY;
    }, { passive: false });
});

/*async function validateSportsJWT() {
 const token = localStorage.getItem("token");

    try {
        const response = await fetch(`/api/auth/check?contact=${val}`);

        if (!response.ok) {
            throw new Error("Server error");
        }

        const exists = await response.json();

        if (exists) {
            localStorage.setItem("contact", val);
            window.location.href = "/1Sports.html";
        } else {
            window.location.href = "/SignIn.html";
        }

    } catch (error) {
        res.textContent = "Unable to connect to server";
        res.style.color = "red";
        console.error(error);
    }
}

async function validateLaptopJWT() {
    const token = localStorage.getItem("token");
     try {
        const response = await fetch(`/api/auth/check?contact=${val}`);

        if (!response.ok) {
            throw new Error("Server error");
        }

        const exists = await response.json();

        if (exists) {
            localStorage.setItem("contact", val);
            window.location.href = "/3Laptop.html";
        } else {
            window.location.href = "/SignIn.html";
        }

    } catch (error) {
        res.textContent = "Unable to connect to server";
        res.style.color = "red";
        console.error(error);
    }
}

async function validateFaceCareJWT() {
    const token = localStorage.getItem("token");
     try {
        const response = await fetch(`/api/auth/check?contact=${val}`);

        if (!response.ok) {
            throw new Error("Server error");
        }

        const exists = await response.json();

        if (exists) {
            localStorage.setItem("contact", val);
            window.location.href = "/2FaceCare.html";
        } else {
            window.location.href = "/SignIn.html";
        }

    } catch (error) {
        res.textContent = "Unable to connect to server";
        res.style.color = "red";
        console.error(error);
    } */

