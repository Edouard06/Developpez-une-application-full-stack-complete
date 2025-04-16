/**
 * Represents a subscription entity linking a user to a theme.
 * 
 * @remarks
 * This interface defines the structure of a subscription, which includes the 
 * unique identifiers for both the user and the theme involved in the subscription.
 * It is used to represent the relationship between a user and a theme in the system.
 * 
 * @interface
 */
export interface Subscription {
    id: number;
    user_id: number;
    theme_id: number;
}