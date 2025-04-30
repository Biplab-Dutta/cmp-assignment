package com.zoroxnekko.palebluecmpassignment.core.presentation

import com.zoroxnekko.palebluecmpassignment.core.domain.DataError
import palebluecmpassignment.composeapp.generated.resources.Res
import palebluecmpassignment.composeapp.generated.resources.error_no_internet
import palebluecmpassignment.composeapp.generated.resources.error_request_timeout
import palebluecmpassignment.composeapp.generated.resources.error_serialization
import palebluecmpassignment.composeapp.generated.resources.error_too_many_requests
import palebluecmpassignment.composeapp.generated.resources.error_unknown

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER -> Res.string.error_unknown
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}
