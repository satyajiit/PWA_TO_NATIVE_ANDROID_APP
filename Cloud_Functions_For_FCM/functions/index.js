const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendNotification = functions.firestore.document("NOTIFICATION_SENDER/{notificationId}").onCreate((snap) => {

		const notificationMessage = snap.data().MESSAGE;
		const titleMessage = snap.data().TITLE;
		const urlDb = snap.data().URL;
      
      var message = {
        data: {
					title: titleMessage,
					body: notificationMessage,
					urlLink: urlDb
        },
        topic: "all"
      };
				return admin.messaging().send(message).then(() => {
				console.log("Notification sent!");
			});
	});
