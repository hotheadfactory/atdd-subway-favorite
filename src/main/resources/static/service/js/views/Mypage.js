import api from '../../api/index.js'

function MyPage() {
    const $email = document.querySelector("#email-field");
    const $name = document.querySelector("#name-field");

    this.init = () => {
        api.member.myinfo().then(data => {
            $email.innerHTML = data.email;
            $name.innerHTML = data.name;
        })
            .catch(error => {
                console.log(error);
            })
    }
}

const myPage = new MyPage()
myPage.init();

