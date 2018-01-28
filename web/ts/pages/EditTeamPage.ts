/// <reference path="Page.ts"/>

/**
 * Defines the player editor page
 *
 * @class EditTeamPage
 */
class EditTeamPage implements Page {
    
        container : JQuery;
    
        /**
         * Initializes the page. This function may phisically manipulate
         * the page and install event handlers.
         */
        init(): boolean {
            this.container = $("#main-container");

            this.container.append(
                $("<form/>", {
                    action: ".",
                    method: "get"
                }).append(
                    $("<label for='name'/>").text("Team Name: ")
                ).append("<input type='text' name='name'/>")
            );
    
            return true;
        }
    
        /**
         * Returns the name of the page
         */
        getName(): string {
            return "Edit Team Information";
        }
    
        /**
         * Removes all elements added by this page.
         */
        destroy(): boolean {
            this.container.empty();
    
            return true;
        }
    }
    