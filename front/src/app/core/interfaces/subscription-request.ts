/**
 * Represents a request to subscribe to a theme.
 * 
 * @remarks
 * This interface defines the structure of the request payload needed to subscribe
 * a user to a specific theme. It is typically used when sending a request to an API
 * endpoint to create a new subscription.
 * 
 * @interface
 */
export interface SubscriptionRequest {
    theme_id: number;
}