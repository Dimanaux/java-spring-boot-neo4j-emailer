class GetParams {
    constructor(obj) {
        this.params = obj;
    }

    valueOf() {
        return '?' + Object.keys(this.params)
            .map(k => `${encodeURIComponent(k)}=${encodeURIComponent(this.params[k])}`)
            .join('&');
    }
}

const app = new Vue({
    el: '#app',
    data: {
        messages: [],
        filter: {
            from: '',
            subject: '',
            content: '',
            to: ''
        }
    },
    methods: {
        search() {
            let response = fetch('/search' + new GetParams(this.filter), {
                method: 'GET',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                credentials: 'include'
            })
                .then(r => r.json())
                .then(messages => {
                    console.log(messages);
                    this.messages = messages;
                });
        }
    }
});

Vue.component('message-item', {
    props: ['message'],
    template: `
        <a :href="'/inbox/messages/' + message.messageId" class="list-group-item list-group-item-action">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">{{message.subject}}</h5>
                <small>{{message.sentAt}}</small>
            </div>
            <p class="mb-1">{{message.content}}</p>
            <small>
                {{message.status}} from {{message.sender}} to {{message.recipients}}
            </small>
        </a>`
});
