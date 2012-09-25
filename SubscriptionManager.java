package com.yourapp.communication;

/**
 * Helper Class to retrieve and update subscriptions.
 * Includes subscriptions in the cache and will create a bigger list from your own separate set of channel data
 * getAppContext is a global utility that I use in the Application.class file.
 * Remember that the hashmap is the unvarnished list and includes the underscores
 * I have a getAppContext utility that I use. You may choose your own context to support the broadcast receiver.
 * @since		2012/03/07
 * @author		John Stack
 */

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.acra.ErrorReporter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;

public class SubscriptionManager {

	Set<String> setOfAllSubscriptions; // retrieved from parse

	public HashMap<String, Boolean> allSubscriptionsAndPossibleSubscriptions // combined list
																// not regexed yet for presentation purposes like in a list.

	public SubscriptionManager() {
	}

	/*
	 * Writes Single New Subscription
	 */
	public void writeNewSubscription(String person) {

	person = _person.replaceAll("( ,)|(,,)|( )|(-)", "_");

		PushService.subscribe(yourApp.getAppContext(), person,
				yourActivity.class);
	}

	/*
	 * Get all subscriptions from local cache and put them into
	 * setOfAllSubscriptions Note this is not regexed for the mandatory
	 * underscore in the channel name.
	 */
	public void getSubscriptions() {

		setOfAllSubscriptions = PushService.getSubscriptions(yourAppapp
				.getAppContext());

		if (setOfAllSubscriptions != null && setOfAllSubscriptions.size() > 0) {

			for (String subs : setOfAllSubscriptions) {
				
//IMPORTANT Gotta Kill the blank for the prompt	- remember to put it back in.
				if (subs !=""){
				allScansAndSubscriptions.put(subs, new Boolean(true));
			}
			}
		}
	}

	/*
	 * Get scans and add them to allScansAndSubscriptions Note this is not
	 * regexed for the mandatory underscore in the channel name.
	 */
	public void getAllScans() {
		try {

			ParseQuery query = new ParseQuery("YourTable");
			query.findInBackground(new FindCallback() {

				@Override
				public void done(List<ParseObject> yourObjects, ParseException p1) {

					if (yourObjects != null && yourObjects.size() > 0) {
						for (ParseObject yourObject : yourObjects) {
							String _person = yourObject
									.getString("person");
							//To get the person to match the format in the subscriptions - which uses an underscore.
							_person = _person.replaceAll(
									"( ,)|(,,)|( )|(-)", "_");

							if (!allSubscriptionsAndPossibleSubscriptions
									.containsKey(_person)) {
								allSubscriptionsAndPossibleSubscriptions.put(_person,
										new Boolean(false));
							}
						}
					}
				}
			});
		} catch (Exception p1) {
			ErrorReporter.getInstance().handleSilentException(p1);
		}
	}

	public void unsubscribe(String person) {
		PushService.unsubscribe(yourApp.getAppContext(), person);
	}

	public void checkSubscription() {
//	Not done
	}
}