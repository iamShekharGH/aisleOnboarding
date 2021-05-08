package com.example.myapplication.network.modelResponse

/*{
    "invites": {
        "profiles": [
            {
                "general_information": {
                    "date_of_birth": "1993-02-09",
                    "date_of_birth_v1": "9th February 1993",
                    "location": {
                        "summary": "IN, Rampur",
                        "full": "Rampur, Uttar Pradesh, IN"
                    },
                    "drinking": "Occasionally",
                    "drinking_v1": {
                        "id": 6,
                        "name": "Drinks Occasionally",
                        "name_alias": "Drinks Occasionally"
                    },
                    "first_name": "Mayank",
                    "gender": "Male",
                    "marital_status": "Single",
                    "marital_status_v1": {
                        "id": 9,
                        "name": "Single",
                        "preference_only": false
                    },
                    "ref_id": "1C76E221",
                    "smoking": "No",
                    "smoking_v1": {
                        "id": 2,
                        "name": "Non-Smoker",
                        "name_alias": "Non-Smoker"
                    },
                    "sun_sign": "Aquarius",
                    "sun_sign_v1": {
                        "id": 2,
                        "name": "Aquarius"
                    },
                    "mother_tongue": {
                        "id": 2,
                        "name": "Hindi"
                    },
                    "faith": {
                        "id": 6,
                        "name": "Hindu"
                    },
                    "height": 62,
                    "cast": null,
                    "kid": null,
                    "diet": null,
                    "politics": null,
                    "pet": null,
                    "settle": null,
                    "age": 28
                },
                "approved_time": 1617950245.2447622,
                "photos": [
                    {
                        "photo": "https://testimages.aisle.co/f39552690128813a6e893b4f4cd725fc729869938.png",
                        "photo_id": 130633,
                        "selected": true,
                        "status": "avatar"
                    },
                    {
                        "photo": "https://testimages.aisle.co/2cb58925723382f002ba3f4d8f573011729866682.png",
                        "photo_id": 130632,
                        "selected": false,
                        "status": null
                    }
                ],
                "user_interests": [],
                "work": {
                    "industry": "Information Technology",
                    "industry_v1": {
                        "id": 13,
                        "name": "Information Technology",
                        "preference_only": false
                    },
                    "monthly_income_v1": null,
                    "experience": "6",
                    "experience_v1": {
                        "id": 5,
                        "name": "6 Years Experience",
                        "name_alias": "6 Years Experience"
                    },
                    "highest_qualification": "Bachelors",
                    "highest_qualification_v1": {
                        "id": 1,
                        "name": "Bachelors",
                        "preference_only": false
                    },
                    "field_of_study": "Engineering",
                    "field_of_study_v1": {
                        "id": 2,
                        "name": "Engineering"
                    }
                },
                "preferences": [
                    {
                        "answer_id": 32669,
                        "id": 10,
                        "value": 0,
                        "preference_question": {
                            "first_choice": "Indoor",
                            "second_choice": "Outdoor"
                        }
                    },
                    {
                        "answer_id": 32671,
                        "id": 15,
                        "value": 0,
                        "preference_question": {
                            "first_choice": "Indie",
                            "second_choice": "Mainstream"
                        }
                    },
                    {
                        "answer_id": 32670,
                        "id": 21,
                        "value": 0,
                        "preference_question": {
                            "first_choice": "Logical",
                            "second_choice": "Emotional"
                        }
                    },
                    {
                        "answer_id": 32668,
                        "id": 2,
                        "value": 1,
                        "preference_question": {
                            "first_choice": "Adopt",
                            "second_choice": "Conceive"
                        }
                    },
                    {
                        "answer_id": 32667,
                        "id": 20,
                        "value": 1,
                        "preference_question": {
                            "first_choice": "Creationist",
                            "second_choice": "Evolutionist"
                        }
                    }
                ],
                "instagram_images": null,
                "last_seen_window": "2021-05-07T15:05:00.892Z",
                "is_facebook_data_fetched": false,
                "icebreakers": null,
                "story": null,
                "meetup": null,
                "verification_status": "verified",
                "latest_poll": null,
                "poll_info": null,
                "has_active_subscription": false,
                "show_concierge_badge": false,
                "lat": "28.7877262873846",
                "lng": "79.01985934828912",
                "last_seen": "Active This Week",
                "online_code": 1,
                "profile_data_list": [
                    {
                        "question": "Philosophy",
                        "preferences": [
                            {
                                "answer_id": 32669,
                                "answer": "Indoor than Outdoor",
                                "first_choice": "Indoor",
                                "second_choice": "Outdoor"
                            },
                            {
                                "answer_id": 32671,
                                "answer": "Indie than Mainstream",
                                "first_choice": "Indie",
                                "second_choice": "Mainstream"
                            },
                            {
                                "answer_id": 32670,
                                "answer": "Logical than Emotional",
                                "first_choice": "Logical",
                                "second_choice": "Emotional"
                            },
                            {
                                "answer_id": 32668,
                                "answer": "Conceive than Adopt",
                                "first_choice": "Conceive",
                                "second_choice": "Adopt"
                            },
                            {
                                "answer_id": 32667,
                                "answer": "Evolutionist than Creationist",
                                "first_choice": "Evolutionist",
                                "second_choice": "Creationist"
                            }
                        ],
                        "invitation_type": "preference"
                    }
                ]
            }
        ],
        "totalPages": 1,
        "pending_invitations_count": 1
    },
    "likes": {
        "profiles": [
            {
                "first_name": "Ajith",
                "avatar": "https://testimages.aisle.co/fdc1bef68fc53c7c489d8a629524a31e728894160.png"
            },
            {
                "first_name": "Ishant",
                "avatar": "https://testimages.aisle.co/58b125e52d319c0390fc2d68b7da2ba6729804903.png"
            }
        ],
        "can_see_profile": false,
        "likes_received_count": 2
    }
}*/

data class ResponseTestProfileList(val code: Int)
