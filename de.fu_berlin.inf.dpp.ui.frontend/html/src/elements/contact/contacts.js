var AmpersandView = require('ampersand-view');
var tmpl = require('./contacts.hbs');
var ContactView = require('./contact');
var dictionary = require('../../dictionary');

module.exports = AmpersandView.extend({
    template: tmpl,
    // Add the dictionary to the context so that the template
    // engine can use it.
    d: dictionary,
    props: {
        collapsed: {
            type: 'boolean',
            default: false
        }
    },
    events: {
        'click h4.collapsible': 'toggleCollapse'
    },
    bindings: {
        'collapsed': [
            // toggle arrow icon down/up
            {
                type: 'booleanClass',
                yes: 'glyphicon-chevron-down',
                no: 'glyphicon-chevron-up',
                hook: 'collapse-trigger'
            },
            // toggle list visibility
            {
                type: 'booleanClass',
                hook: 'contact-list',
                name: 'hide'
            }
        ]
    },
    render: function() {

        this.renderWithTemplate(this);
        this.renderCollection(this.collection, ContactView,
            this.queryByHook('contact-list'));
        return this;
    },
    toggleCollapse: function() {

        this.collapsed = !this.collapsed;
    }
});
