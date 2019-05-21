const app = new Vue({
    el: '#app',
    data: {
        recipientsEmails: [],
        copyRecipientsEmails: [],
        secretCopyRecipientsEmails: [],
        newRecipient: '',
        newCopyRecipient: '',
        newSecretCopyRecipient: ''
    },
    methods: {
        createEmailField(arr, name, value) {
            if (value.trim() === '') return;
            const newField = {
                id: arr.length,
                email: value,
            };
            newField.name = `${name}[${arr.length}]`;
            if (arr.filter(field => field.email === newField.email).length === 0) {
                arr.push(newField);
            }
            arr = arr.filter(e => e.email.trim() !== '');
        },
        createRecipient() {
            this.createEmailField(this.recipientsEmails, 'recipients', this.newRecipient);
            this.newRecipient = '';
        },
        createCopyRecipient() {
            this.createEmailField(this.copyRecipientsEmails, 'copyRecipients', this.newCopyRecipient);
            this.newCopyRecipient = '';
        },
        createSecretCopyRecipient() {
            this.createEmailField(this.secretCopyRecipientsEmails, 'secretCopyRecipients', this.newSecretCopyRecipient);
            this.newSecretCopyRecipient = '';
        }
    }
});

Vue.component('recipient-email-item', {
    props: ['field'],
    template: '<input class="list-group-item" :name="field.name" :value="field.email" v-on:contextmenu="removeElement(field.id)" readonly>',
    methods: {
        removeElement(id) {
            app.recipientsEmails = app.recipientsEmails.filter(e => e.id !== id);
            app.copyRecipientsEmails = app.copyRecipientsEmails.filter(e => e.id !== id);
            app.secretCopyRecipientsEmails = app.secretCopyRecipientsEmails.filter(e => e.id !== id);
        }
    }
});

const changeAction = formAction => document.getElementById('app').action = formAction;
