package tm.com.balary.features.product.presentation.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tm.com.balary.features.home.presentation.ui.product.HomeSectionSkeleton
import tm.com.balary.ui.skeleton.SkeletonRounded

@Composable
fun ProductDetailSkeleton(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth().verticalScroll(
            rememberScrollState()
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SkeletonRounded(
                modifier = Modifier.fillMaxWidth().height(340.dp),
                borderRadius = 20.dp
            )

            SkeletonRounded(
                modifier = Modifier.height(15.dp),
                borderRadius = 4.dp
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkeletonRounded(
                    modifier = Modifier.fillMaxWidth(0.6f).height(25.dp),
                    borderRadius = 4.dp
                )

                SkeletonRounded(
                    modifier = Modifier.width(50.dp).height(25.dp),
                    borderRadius = 4.dp
                )
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkeletonRounded(
                    modifier = Modifier.width(100.dp).height(25.dp),
                    borderRadius = 4.dp
                )

                SkeletonRounded(
                    modifier = Modifier.width(50.dp).height(25.dp),
                    borderRadius = 4.dp
                )
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkeletonRounded(
                    modifier = Modifier.fillMaxWidth(0.4f).height(25.dp),
                    borderRadius = 4.dp
                )

                SkeletonRounded(
                    modifier = Modifier.width(50.dp).height(25.dp),
                    borderRadius = 4.dp
                )
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkeletonRounded(
                    modifier = Modifier.weight(1f).height(25.dp),
                    borderRadius = 4.dp
                )

                SkeletonRounded(
                    modifier = Modifier.width(50.dp).height(25.dp),
                    borderRadius = 4.dp
                )
            }

            repeat(6) {
                SkeletonRounded(
                    modifier = Modifier.fillMaxWidth().height(25.dp),
                    borderRadius = 4.dp
                )
            }


        }
        HomeSectionSkeleton(
            modifier = Modifier.fillMaxWidth(),
            adsCount = 0
        )
    }

}